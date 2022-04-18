import javax.sound.sampled.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AudioStreamer {
  public static void main(String[] args) throws Exception {
    int port = AudioListener.PORT; // 送信先のポート番号
    String ip = AudioListener.IP; // 送信先のポート番号

    DatagramSocket socket = new DatagramSocket(); // データグラムソケットを作成
    InetAddress address = InetAddress.getByName(ip); // 送信先のアドレス

    AudioFormat format = AudioData.format; // 音声フォーマット形式の作成
    TargetDataLine line = AudioSystem.getTargetDataLine(format); // マイクリソースから音声を取得するラインを作成

    int bufferSize = AudioData.bufferSize; // バッファサイズ
    byte[] buf = new byte[bufferSize]; // データを格納するバッファ

    try {
      line.open(format); // インプット用ラインを開く
      line.start(); // 音声の取得開始

      // バッファサイズまで音声データ格納 -> 送信を繰り返す
      while (true) {
        line.read(buf, 0, buf.length); // バッファに音声データを書き込む
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, port); // パケットを作成
        socket.send(sendPacket); // パケットを送信
      }

    } finally {
      line.close(); // データラインを閉じる
      socket.close(); // データグラムソケットを閉じる
    }
  }
}