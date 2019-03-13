package com.github.digitalsoftwaresolutions;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;

import java.io.File;
import java.io.FileDescriptor;

public class Tun2SocksAPI {
    public static boolean sendFd(FileDescriptor[] fileDescriptors , File sock_path , int maximum_tries){
        int tries = 0;
        LocalSocketAddress socketAddress = new LocalSocketAddress(sock_path.getAbsolutePath(), LocalSocketAddress.Namespace.FILESYSTEM);
        LocalSocket socket = new LocalSocket();
        while (tries < maximum_tries){
            try {
                Thread.sleep(500);
                socket.connect(socketAddress);
                socket.setFileDescriptorsForSend(fileDescriptors);
                socket.getOutputStream().write(42);
                return true;
            }catch (Exception err){
                tries++;
            }
        }
        return false;
    }
}
