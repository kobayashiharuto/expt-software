import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AudioListener {
  static final int PORT = 8080; // リスナーのポート番号を指定
  static final String IP = "localhost"; // リスナーのIPアドレスを指定

  public static void main(String[] args) throws Exception {
    AudioFormat format = AudioData.format; // 音声フォーマットを指定
    SourceDataLine line = AudioSystem.getSourceDataLine(format); // スピーカーから音声データを出力するラインを取得

    DatagramSocket socket = new DatagramSocket(PORT); // ポート番号を指定してデータグラムソケットを作成

    int bufferSize = AudioData.bufferSize; // バッファサイズ
    byte[] buf = new byte[bufferSize]; // データを格納するバッファ

    try {
      line.open(format); // 音声データを出力するラインを開く
      line.start(); // 流れてきたデータをスピーカーに出力する状態に

      // データが流れてくるまで待機 -> データが送られてきたらアウトプットデータラインに流す を繰り返す
      while (true) {
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length); // 受け取り用バッファを格納したパケットクラスを作成
        socket.receive(receivePacket); // パケットを受信
        line.write(buf, 0, buf.length); // バッファをアウトプットデータラインに流す（再生）
      }
    } finally {
      line.close(); // 音声データを出力するラインを閉じる
      socket.close(); // データグラムソケットを閉じる
    }
  }
}