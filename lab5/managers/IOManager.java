package managers;

import java.io.*;


enum ReadMod {console, file}

public class IOManager {
    private BufferedReader bufferedReader;
    private BufferedOutputStream bufferedOutputStream;
    private ReadMod readMod = ReadMod.console;

    public IOManager(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedOutputStream = new BufferedOutputStream(System.out);
    }

    public void setInputFile(String path) throws IOException{
        File file = new File(path);
        bufferedReader = new BufferedReader(new FileReader(file));
        readMod = ReadMod.file;
    }

    public void setOutputFile(String path) throws IOException{
        File file = new File(path);
        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
    }

    public boolean hasNext() {
        try {
            return bufferedReader.ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readNext() {
        StringBuilder builder = new StringBuilder();
        try {
            while (true) {
                    if (readMod == ReadMod.file && !hasNext()) break;
                char c = (char) bufferedReader.read();
                if (c == '\r') continue;
                if (c == '\n') break;
                if (builder.length() != 0 && Character.isWhitespace(c)) break;
                builder.append(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
            }
        return builder.toString();
    }

    public String readUntilEnd() throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNext()) {
            char c = (char)bufferedReader.read();
            builder.append(c);
        }
        return builder.toString();
    }

    public void write(String s) {
        try {
            bufferedOutputStream.write(s.getBytes());
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String s) {
        write(s + "\n");
    }
}
