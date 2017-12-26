package com.betterlxc.map;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.drew.imaging.ImageMetadataReader.readMetadata;

/**
 * Created by LXC on 2017/4/24.
 */
public class MapsTest {

  private static void print(Metadata metadata) {
    System.out.println("-------------------------------------");
    //遍历图片中的元数据
    for (Directory directory : metadata.getDirectories()) {
      for (Tag tag : directory.getTags()) {
        System.out.println(tag);
      }
      if (directory.hasErrors()) {
        for (String error : directory.getErrors()) {
          System.err.println("ERROR: " + error);
        }
      }
    }
  }

  @Test
  public void test() throws ImageProcessingException, IOException {
    File file = new File("/Users/lxc/Downloads/1.jpg");
    Metadata metadata = readMetadata(file);
    print(metadata);
  }

}
