package maj06mar11;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FileChooser
{
    public static String chooseFile() 
    {
        String name="H:\\in lab\\home9april\\src\\maj06mar11\\ref_str.txt";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select file for reading Reference String");
        int returnValue = jfc.showDialog(null, " SELECT ");
        if (returnValue == JFileChooser.APPROVE_OPTION) 
            name=jfc.getSelectedFile().getPath();
        return name;
    } 
}