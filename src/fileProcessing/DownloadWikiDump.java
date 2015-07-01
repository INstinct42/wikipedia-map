package fileProcessing;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by jonas on 15.06.15.
 */
public class DownloadWikiDump {
    private static final File OUTFILE = new File("./out/dewiki-latest-pages-articles.xml.bz2");
    private final URL URL = new URL("https://dumps.wikimedia.org/dewiki/latest/dewiki-latest-pages-articles.xml.bz2");
    //private final URL URL = new URL("https://dumps.wikimedia.org/dewiki/latest/dewiki-latest-pages-articles.xml.bz2-rss.xml");
    private URLConnection connection;
    private InputStream is;
    private ReadableByteChannel rbc;

    public DownloadWikiDump() throws IOException {
        connection = URL.openConnection();
        is = connection.getInputStream();
        rbc = Channels.newChannel(URL.openStream());
    }

    public void download() throws IOException {
        FileOutputStream fos = new FileOutputStream(OUTFILE);
        fos.getChannel().transferFrom(this.rbc, 0, Long.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException {
        DownloadWikiDump dwd = new DownloadWikiDump();
        dwd.download();
    }

}
