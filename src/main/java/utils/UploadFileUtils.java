package utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;
import org.springframework.util.FileCopyUtils;
import net.coobird.thumbnailator.Thumbnails;

public class UploadFileUtils {
  static final int THUMB_WIDTH = 300;

  static final int THUMB_HEIGHT = 300;

  public static String fileUpload(String uploadPath, String fileName, byte[] fileData,
      String ymdPath) throws Exception {
    UUID uid = UUID.randomUUID();
    String newFileName = uid + "_" + fileName;
    String imgPath = String.valueOf(uploadPath) + ymdPath;
    File target = new File(imgPath, newFileName);
    FileCopyUtils.copy(fileData, target);
    String thumbFileName = "s_" + newFileName;
    File image = new File(String.valueOf(imgPath) + File.separator + newFileName);
    File thumbnail =
        new File(String.valueOf(imgPath) + File.separator + "s" + File.separator + thumbFileName);
    if (image.exists()) {
      thumbnail.getParentFile().mkdirs();
      Thumbnails.of(new File[] {image}).size(300, 300).toFile(thumbnail);
    }
    return newFileName;
  }

  public static String calcPath(String uploadPath) {
    Calendar cal = Calendar.getInstance();
    String yearPath = String.valueOf(File.separator) + cal.get(1);
    String monthPath = String.valueOf(yearPath) + File.separator
        + (new DecimalFormat("00")).format((cal.get(2) + 1));
    String datePath =
        String.valueOf(monthPath) + File.separator + (new DecimalFormat("00")).format(cal.get(5));
    makeDir(uploadPath, new String[] {yearPath, monthPath, datePath});
    makeDir(uploadPath, new String[] {yearPath, monthPath, String.valueOf(datePath) + "\\s"});
    return datePath;
  }

  private static void makeDir(String uploadPath, String... paths) {
    if ((new File(paths[paths.length - 1])).exists())
      return;
    byte b;
    int i;
    String[] arrayOfString;
    for (i = (arrayOfString = paths).length, b = 0; b < i;) {
      String path = arrayOfString[b];
      File dirPath = new File(String.valueOf(uploadPath) + path);
      if (!dirPath.exists())
        dirPath.mkdir();
      b++;
    }
  }
}
