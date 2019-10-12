import UIKit
import shared

class ViewController: UIViewController, FeedView {

    func showError(error: KotlinThrowable) {
        activityIndicatorView.stopAnimating()
    }

    func showData(feed: Feed) {
        activityIndicatorView.stopAnimating()
        self.tableView.separatorStyle = .singleLine
        self.posts = feed.items
        self.tableView.reloadData()
    }

    func showLoading() {
        self.tableView.separatorStyle = .none
        activityIndicatorView.startAnimating()
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.addSubview(tableView)
        NSLayoutConstraint.activate([
            tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            tableView.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            tableView.topAnchor.constraint(equalTo: view.topAnchor)
        ])

        tableView.backgroundView = activityIndicatorView
        presenter.loadData()
    }

    private var posts = [Item]()
    private lazy var presenter = PresenterFactory.Companion().createFeedPresenter(view: self)
    private lazy var activityIndicatorView = UIActivityIndicatorView(activityIndicatorStyle: .gray)

    private lazy var tableView: UITableView = {
        var tableView = UITableView()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "MyCell")
        tableView.dataSource = self
        tableView.delegate = self
        tableView.separatorStyle = .none
        tableView.translatesAutoresizingMaskIntoConstraints = false
        return tableView
    }()
}

extension ViewController: UITableViewDelegate, UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return posts.isEmpty ? 0 : 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MyCell", for: indexPath as IndexPath)
        cell.accessoryType = .disclosureIndicator
        cell.textLabel!.text = posts[indexPath.row].title
        return cell
    }
}
