import UIKit
import SwiftUI
import ComposeApp

class ComposeViewController: UIViewController {
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let mainController = MainViewControllerKt.MainViewController()
        let container = ComposeViewController()
        container.addChild(mainController)
        container.view.addSubview(mainController.view)
        mainController.view.frame = container.view.bounds
        mainController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        mainController.didMove(toParent: container)
        return container
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard)
            .edgesIgnoringSafeArea(.all)
    }
}
