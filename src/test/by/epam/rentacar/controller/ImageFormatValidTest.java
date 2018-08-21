package by.epam.rentacar.controller;

import by.epam.rentacar.controller.util.UploadHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ImageFormatValidTest {

    private String filename;

    public ImageFormatValidTest(String filename) {
        this.filename = filename;
    }

    @Parameterized.Parameters
    public static List<String> filenamesForTest() {
        return Arrays.asList(
                "photo.jpg",
                "car.photo.jpeg",
                "my-photo.png",
                "avatar_.gif"
        );
    }

    @Test
    public void testFiles() {
        Assert.assertTrue(UploadHelper.checkFileFormat(filename));
    }

}
