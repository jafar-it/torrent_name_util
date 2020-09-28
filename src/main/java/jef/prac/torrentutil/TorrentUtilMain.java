package jef.prac.torrentutil;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.libtorrent4j.TorrentInfo;

public class TorrentUtilMain {
	
	
	
	public static void main(String... args) throws Exception {
		
		
		File rootDir = null;
		
		if(args.length>0 && args[0] != null) {
			
			String path = args[0];
			
			rootDir = new File(path);
			
			if(!rootDir.exists() || !rootDir.isDirectory() ) {
				throw new Exception("Either file does not exist or is not a directory.");
			}
			
		} else {
			rootDir = new File(".");
		}
		
		
		if(rootDir==null) {
			throw new Exception("Internal Error");
		}
		
		Collection<File> torrFileList =  FileUtils.listFiles(rootDir, new String[] {"torrent"}, true);
		
		for(File torrFile : torrFileList) {
			
			TorrentInfo torrInfo = new TorrentInfo(torrFile);
			
			System.out.println("torr File :"+torrInfo.name());
			System.out.println("torr File :"+torrInfo.files().name());
			System.out.println("torr File :"+torrInfo.origFiles());
			
			String fileName = torrInfo.name().replace(" ", "_")+".torrent";
			
			torrFile.renameTo(new File(rootDir,fileName));
		}
	}

}
