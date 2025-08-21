# Java CLI アプリ

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
gradle run
```
