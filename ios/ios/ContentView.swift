//
//  ContentView.swift
//  ios
//
//  Created by Henry Xu on 2022-12-08.
//

import SwiftUI
import CoreData
import shared

class ImageGenerator: ObservableObject {
    let client: OpenAiClient
    
    init(apiKey: String) {
        client = OpenAiClientBuilder(apiKey: apiKey).build()
    }
    
    @Published var images = [String]()
    
    func generateImages(prompt: String) async {
        let request = CreateImageRequest(prompt: prompt, n: 2, size: nil, responseFormat: nil, user: nil)
        let result = try? await client.api.createImage(request: request)
        guard let results = result?.result?.data else {
            return
        }
        await MainActor.run {
            images = results.map { $0.url ?? "" }
        }
    }
}

struct ContentView: View {
    @EnvironmentObject var generator: ImageGenerator
    
    @State private var loading: Bool = false
    @State private var prompt: String = ""
    @FocusState private var promptFieldIsFocused: Bool?
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                TextField(
                    "Prompt",
                    text: $prompt
                )
                .focused($promptFieldIsFocused, equals: true)
                .onSubmit {
                    promptFieldIsFocused = false
                    loading = true
                    Task {
                        await generator.generateImages(prompt: prompt)
                        loading = false
                    }
                }.padding()
                
                if (loading) {
                    ProgressView("Generating")
                }
                
                List {
                    ForEach(generator.images, id: \.self) { image in
                        NavigationLink {
                            Text(image)
                        } label: {
                            AsyncImage(
                                url: URL(string: image),
                                content: { res in
                                    res.resizable()
                                        .aspectRatio(contentMode: .fit)
                                        .frame(maxWidth: .infinity)
                                },
                                placeholder: {
                                    ProgressView()
                                }
                            )
                        }
                    }
                }
                .toolbar {
                    
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView().environmentObject(ImageGenerator(apiKey: ""))
    }
}
