//
//  iosApp.swift
//  ios
//
//  Created by Henry Xu on 2022-12-08.
//

import SwiftUI

@main
struct iosApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
