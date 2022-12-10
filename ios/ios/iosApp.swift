//
//  iosApp.swift
//  ios
//
//  Created by Henry Xu on 2022-12-08.
//

import SwiftUI

@main
struct iosApp: App {
    let key = Bundle.main.infoDictionary?["OPENAI_API_KEY"] as? String
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(ImageGenerator(apiKey: key ?? "invalid"))
        }
    }
}

