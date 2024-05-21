## ANDIE - A Non-Destructive Image Editor
ANDIE is an image processing and editing program, a bit like Photoshop. As ANDIE is a non-destructive image editor, many image processing operations, such as blurring filters, cannot be reversed because information is lost in the process. ANDIE stores the original image and a sequence of the operations, these operations can then be applied to a copy of the original image to get the desired result. Since the original image and the full sequence of operations is kept, no information is lost.

# How ANDIE Was Tested
ANDIE was tested thoroughly by running the program through different devices and operating systems to ensure optimal performance and compatibility. Throughout the development process, each ANDIE feature was tested to identify and address any potential issues. It was also run to test usability to gather feedback and address user concerns that may be face as a result of using the program.

To test the user interface of the ANDIE program, individuals were invited to test its features and assess whether their implementation and descriptions met their expectations. Feedback from these participants was collected and utilized to refine and enhance the interface and to ensure the program was user-friendly.

## Contributions
**Sharpen Filter** - Sebastian Chkourko

**Gaussian Blur Filter** - Jessica Fan

**Median Filter** - Kruti Mistry

**Image Inversion** - James Maher

**Colour Channel Cycling** - James Maher

**Multilingual Support** - Sebastian Chkourko, Diego Olivera, Jessica Fan, Kruti Mistry

**Image Resize** - Diego Olivera

**Image Rotations: 90 Degree Left; 90 Degree Right; 180 Degrees;** - Diego Olivera

**Image Flip: Horizontal; Vertical;** - Diego Olivera

**Image Export** - Jessica Fan

**Exception Handling** - Sebastian Chkourko

**Other Error Avoidance/Prevention** - Jessica Fan, Kruti Mistry

**Extended filters** - Sebastian Chkourko

**Filters with negative results** - Jessica Fan, Kruti Mistry

**Emboss and edge detection filters** - Kruti Mistry

**Brightness adjustment** - Diego Olivera

**Contrast adjustment** -  Diego Olivera

**Block averaging** - Diego Olivera

**Random scattering** - Diego Olivera

**Toolbar for common operations** - Jessica Fan, Kruti Mistry

**Keyboard shortcuts** - Sebastian Chkourko

**Mouse selection of rectangular regions** - James Maher, Jessica Fan

**Crop to selection** Diego Olivera, Jessica Fan

**Drawing functions – rectangle, ellipse, line** - Jessica Fan

**Macros for record and replay of operations** - Sebastian Chkourko

**Show us something. . .: ANDIE Help Guide, Change Toolbar Colour, Freehand Drawing** - Sebastian Chkourko, Jessica Fan


## ANDIE Build Instructions
Run ANDIE through Gradle.

## ANDIE User Guide
This user guide serves as a comprehensive reference, providing insights into each feature's usage, restrictions, and feedback mechanisms. By adhering to these guidelines, users can maximize ANDIE's capabilities. 

**All Features** - Click the corresponding button for the desired feature or use the provided shortcut. The program uses input controls, to enforce valid input ranges for all applicable features, ensuring ease of use and preventing errors.

**Image Save As, Image Export** - To use "Save As" and "Export," include the image type in the new filename. For example, to save a PNG image with the filename "imageFile," name it "imageFile.png."

**Mean Filter, Gaussian Blur Filter, Median Filter** - The radius value for these filters must be between 1 and 10. The program enforces these limits using a JSpinner with minimum and maximum bounds.

**Resize** - The resize value for the current image must be between 25 (25% of the current image size) and 300 (300% of the current image size). This is controlled within the program using a JSpinner.

**Colour Channel Cycling** - This feature cycles through different possible re-orderings of RGB channels, such as RGB, GRB, and BRG.

**Drawing Stroke Width** - The number inputted for the stroke width must be between 1 and 10. The program ensures this range using a JSpinner.

**Choose Language** - To change the language, go to the "Settings" menu and click the "Change Language" button. A pop-up with a drop-down menu of available languages will appear. Choose your preferred language and restart ANDIE.

**Crop to Selection and Drawing Functions** - To use these features, press and hold the mouse button, then release it when you want the crop or drawing to end.

## Issues and Bugs
**Median Filter** - The Median Filter operation, while effective, may experience a delay in processing and application, this is particularly noticeable on larger images. This delay can extend up to approximately 10 seconds, depending on the size and complexity of the image. 

**Colour Channel Cycling** - While colour channel cycling does cycle through all RGB variations, the order in which these variations appear may not follow a predictable sequence.
