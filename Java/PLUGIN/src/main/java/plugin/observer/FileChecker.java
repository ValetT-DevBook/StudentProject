package plugin.observer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Allows to create a FileChecker
 */
public class FileChecker {

    private FilenameFilter filter;
    private File file;
    private List<FileListener> listListener;
    private List<String> listFile;

    /**
     * Constructor of the class
     * @param filter a filter
     */
    public FileChecker(FilenameFilter filter) {
        this.filter = filter;
        this.listListener = new ArrayList<FileListener>();
        this.listFile = new ArrayList<String>();
    }

    /**
     * Give the filename of the filter
     * @return a filename
     */
    public FilenameFilter getFilenameFilter() {
        return this.filter;
    }

    /**
     * Allows to add file in FileListner
     * @param l the file listener
     */
    public void addFileListener(FileListener l) {
        if (! this.listListener.contains(l))
            this.listListener.add(l);
    }

    /**
     * Allows to remove from the FileListener
     * @param l the file listener
     */
    public void removeFileListener(FileListener l) {
        this.listListener.remove(l);
    }

    /**
     * Allows to launch a file to add 
     * @param filename file
     */
    private void fireFileAdded(String filename) {
        if (this.listListener.size() == 0) return ;
        FileEvent event = new FileEvent(filename);

        for (FileListener fl : this.listListener) {
            fl.fileAdded(event);
        }
    }

    /**
     * Allows to launch a file to reomve
     * @param filename file
     */
    private void fireFileRemove(String filename) {
        if (this.listListener.size() == 0) return ;
        FileEvent event = new FileEvent(filename);

        for (FileListener fl : this.listListener) {
            fl.fileRemoved(event);
        }
    }

    /**
     * Allows to look if something is happening
     */
    private void somethingHappen(){
        String arr[] = this.file.list(this.filter);
        if (arr == null) return;

        List<String> l = Arrays.asList(arr);

        for (String s : l) {
            if (! this.listFile.contains(s)) {
                this.listFile.add(s);
                fireFileAdded(s);
            }
        }

        for (String s : new ArrayList<String>(this.listFile)) {
            if (! l.contains(s)) {
                this.listFile.remove(s);
                fireFileRemove(s);
            }
        }
            
    }

    /**
     * Allows to start
     * @param pathname a pathname
     */
    public void start(String pathname){
        this.file = new File(pathname);
        Timer timer = new Timer(1000, new TimeCheckListener());
        timer.start();
    }

    private class TimeCheckListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            FileChecker.this.somethingHappen();
		}
    }
    
}