# Java CLI アプリ

DDD に基づいて設計した CLI アプリケーションです。

- domain 層
- application 層
- infrastructure 層
- presentation 層

の 4 層アーキテクチャで構成しています。

## パッケージ構成

```tree
└── 📁stocktradarapp
    └── 📁application
        └── 📁common
            └── 📁helper
                ├── RequestResult.java
                ├── RequestStatus.java
        └── 📁stock
            └── 📁dto
                └── 📁request
                    ├── StockRegistrationCommand.java
                └── 📁response
                    ├── StockDto.java
            └── 📁service
                └── 📁usecase
                    └── 📁get
                        ├── StockGetService.java
                    └── 📁register
                        ├── StockRegistrationException.java
                        ├── StockRegistrationService.java
    └── 📁config
        ├── CliAppConfig.java
        ├── CliRoutingConfig.java
        ├── StockCsvConfig.java
    └── 📁domain
        └── 📁stock
            └── 📁exception
                ├── StockValidationException.java
            └── 📁valueobject
                ├── Market.java
                ├── SharesIssued.java
                ├── StockName.java
                ├── Ticker.java
            ├── Stock.java
            ├── StockDomainService.java
            ├── StockFactory.java
            ├── StockRepository.java
    └── 📁infrastructure
        └── 📁factory
            └── 📁stock
                └── 📁command
                    ├── CliStockFactory.java
        └── 📁repository
            └── 📁stock
                └── 📁csv
                    └── 📁entity
                        ├── StockEntity.java
                    └── 📁helper
                        ├── CsvValidator.java
                        ├── EntityValidator.java
                    ├── CsvStockRepository.java
    └── 📁presentation
        └── 📁cli
            └── 📁controller
                ├── CliAppController.java
                ├── DisplayMenuController.java
                ├── HomeMenuController.java
                ├── RegistrationMenuController.java
            └── 📁exception
                ├── UserQuitException.java
            └── 📁helper
                └── 📁table
                    ├── TableColmunSpec.java
                    ├── TableMaker.java
                    ├── TableRenderable.java
                └── 📁validation
                    ├── ValidationResult.java
                    ├── ValidationStatus.java
            └── 📁router
                ├── MenuRouter.java
            └── 📁view
                ├── ApplicationExitView.java
                ├── HomeMenuView.java
                ├── StockDisplayView.java
                ├── StockRegistrationView.java
            └── 📁viewmodel
                └── 📁menu
                    ├── ExecutableMenuItem.java
                    ├── Menu.java
                └── 📁stock
                    ├── StockDisplayItem.java
    └── StockTraderApplication.java
```

## 環境構築

1. SDKMAN のインストール

   ```bash
   curl -s "https://get.sdkman.io" | bash
   source "~/.sdkman/bin/sdkman-init.sh"
   ```

2. Java のインストール

   ```bash
   sdk install java 21.0.2-open
   ```

3. Gradle のインストール

   ```bash
   sdk install gradle
   ```

## アプリケーションの起動

```bash
gradle bootrun --console=plain
```
