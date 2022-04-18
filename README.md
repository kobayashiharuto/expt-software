# ソフトウェア製作調査
理工学実験のソフトウェア制作のためのテストリポジトリ

## 注意
ハウリング対策をしてないのでイヤホンなどで対策してください

## 各ファイル
* AudioData.java: 音声フォーマット等の定数管理用
* AudioEcho.java: デバイスのマイクに入力するとデバイスのスピーカーから出力される（ローカルテスト用）
* AudioStreamer.java: デバイスのマイクに入力すると AudioListener に対して UDP で音声データが送る
* AudioListener.java: AudioStreamerから送られてきた音声をスピーカーから再生する
