/*
 * 
 */
package treeview;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Level;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.InfoTool;

/**
 * Manages the SystemTree.
 *
 * @author GOXR3PLUS
 */
public class SystemRoot {
	
	/** The Constant folderImage. */
	public static final Image folderImage = new Image(
	        SystemFileItem.class.getResourceAsStream(InfoTool.images + "folder.png"));
	
	/** The Constant openedFolderImage. */
	public static final Image openedFolderImage = new Image(
	        SystemFileItem.class.getResourceAsStream(InfoTool.images + "openedFolder.png"));
	
	/** The Constant fileImage. */
	public static final Image fileImage = new Image(
	        SystemFileItem.class.getResourceAsStream(InfoTool.images + "file.png"));
	
	/** The host name. */
	String hostName = "computer";
	
	/** The root. */
	SystemFileItem root;
	
	/** The root directories. */
	Iterable<Path> rootDirectories;
	
	/**
	 * Constructor.
	 */
	public SystemRoot() {
		
		// setup the file browser root
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			Main.logger.log(Level.WARNING, "", ex);
		}
		root = new SystemFileItem(hostName);
		root.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(InfoTool.images + "computer.png"))));
		
		SystemFileItem userHome = new SystemFileItem(System.getProperty("user.home"));
		root.getChildren().add(userHome);
		rootDirectories = FileSystems.getDefault().getRootDirectories();
		for (Path name : rootDirectories) {
			SystemFileItem treeNode = new SystemFileItem(name.toString());
			root.getChildren().add(treeNode);
		}
		
		root.setExpanded(true);
	}
	
	/**
	 * @return The root item of the FileSystem
	 */
	public SystemFileItem getRoot() {
		return root;
	}
}
