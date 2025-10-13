import SwiftUI

@main
struct iOSApp: App {
    init() {
        // Ensure the window style is configured properly
        if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene {
            windowScene.windows.first?.overrideUserInterfaceStyle = .dark
        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
                .background(WindowBackgroundModifier())
        }
    }
}

struct WindowBackgroundModifier: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let viewController = UIViewController()
        viewController.view.backgroundColor = .clear
        return viewController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        uiViewController.view.backgroundColor = .clear
    }
}
