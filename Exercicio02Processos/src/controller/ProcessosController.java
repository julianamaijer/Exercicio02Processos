package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {

    private String os;

    public ProcessosController() {
        os = null;
    }

    public String os(String os) {
        os = System.getProperty("os.name");
        return os;
    }

    public void callProcess(String process) {
        try {
            Runtime.getRuntime().exec(process);
        }catch(Exception e) {
            String msgErro = e.getMessage();
            if (msgErro.contains("740")) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("cmd /c");
                buffer.append(" ");
                buffer.append(process);
                try {
                    Runtime.getRuntime().exec(buffer.toString());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else {
                System.err.println(msgErro);
            }
        }
    }

    public void readProcess (String process) {
        try {
            Process p = Runtime.getRuntime().exec(process);
            InputStream fluxo = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leitor);
            String linha = buffer.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = buffer.readLine();
            }
            buffer.close();
            leitor.close();
            fluxo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void killProcessWindows(String param) {
        String cmdPid = "TASKKILL /PID";
        String cmdNome = "TASKKILL /IM";
        int pid = 0;
        StringBuffer buffer = new StringBuffer();

        try {
            //TASKKILL /PID XXXX
            pid = Integer.parseInt(param);
            buffer.append(cmdPid);
            buffer.append(" ");
            buffer.append(pid);
        }catch(NumberFormatException e){
            //TASKKILL /IM nomeprocesso.exe
            buffer.append(cmdNome);
            buffer.append(" ");
            buffer.append(param);
        }

        callProcess(buffer.toString());
    }

    public void killProcessLinux(String param) {
        String cmdPid = "killall";
        String cmdNome = "killall";
        int pid = 0;
        StringBuffer buffer = new StringBuffer();

        try {
            pid = Integer.parseInt(param);
            buffer.append(cmdPid);
            buffer.append(" ");
            buffer.append(pid);
        }catch(NumberFormatException e){
            buffer.append(cmdNome);
            buffer.append(" ");
            buffer.append(param);
        }

        callProcess(buffer.toString());
    }
}