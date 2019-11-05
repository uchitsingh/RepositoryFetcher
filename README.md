"# RepositoryFetcher" 

Get the list of repositories from your GitHub and display it in a recyclerView.
Base url is https://api.github.com/ and the endpoint is users/:username/repository were :username would be your GitHub username.
The documentation for repositories endpoint is here https://developer.github.com/v3/repository/#list-your-repositories
Uses Kotlin and MVVM architecture with components LiveData and ViewModel. Rx used to set up asynchronous stream of data. 
