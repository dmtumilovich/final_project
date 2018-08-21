package by.epam.rentacar.controller;

import by.epam.rentacar.controller.util.UploadHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ImageFormatInvalidTest {

    private String filename;

    public ImageFormatInvalidTest(String filename) {
        this.filename = filename;
    }

    @Parameterized.Parameters
    public static List<String> filenamesForTest() {
        return Arrays.asList(
                "photo.txt",
                "car.photo.pdf",
                "my-photo.jpeeeg"
        );
    }

    @Test
    public void testFiles() {
        Assert.assertFalse(UploadHelper.checkFileFormat(filename));
    }

}
