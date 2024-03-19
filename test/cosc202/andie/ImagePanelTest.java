package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class ImagePanelTest {
    @Test
    void initialDummyTest(){
        
    }

    @Test
    void getZoomInitialValue(){
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0, testPanel.getZoom());
    }

    @Test
    void getZoomAftersetZoom(){
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom() == 100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
    }

    @Test
    void getZoomInitialValue2(){
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(-1, testPanel.getZoom());
    } 

}