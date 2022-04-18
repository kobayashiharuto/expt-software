import javax.sound.sampled.*;

public class AudioEcho {

  public static void main(String[] args) throws Exception {
    AudioFormat format = AudioData.format; // 音声フォーマット形式の作成

    TargetDataLine inputDataLine = AudioSystem.getTargetDataLine(format); // マイクリソースから音声を取得するラインを作成
    SourceDataLine outputDataLine = AudioSystem.getSourceDataLine(format); // スピーカーから音声データを出力するラインを取得

    int bufferSize = AudioData.bufferSize; // バッファサイズ
    byte[] buf = new byte[bufferSize]; // データを格納するバッファ

    inputDataLine.open(format); // インプット用ラインを開く
    inputDataLine.start(); // 音声の取得開始

    outputDataLine.open(format); // アウトプット用ラインを開く
    outputDataLine.start(); // 流れてきたデータをスピーカーに出力する状態に

    try {
      while (true) {
        inputDataLine.read(buf, 0, buf.length); // 音声データをバッファに書き込む
        outputDataLine.write(buf, 0, buf.length); // バッファをアウトプットデータラインに流す（再生）
      }

    } finally {
      outputDataLine.close(); // ラインを閉じる
      inputDataLine.close(); // ラインを閉じる
    }
  }
}
