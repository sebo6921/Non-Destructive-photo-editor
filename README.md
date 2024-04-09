## ANDIE - A Non-Destructive Image Editor
ANDIE is an image processing and editing program, a bit like Photoshop. As ANDIE is a non-destructive image editor, many image processing operations, such as blurring filters, cannot be reversed because information is lost in the process. ANDIE stores the original image and a sequence of the operations, these operations can then be applied to a copy of the original image to get the desired result. Since the original image and the full sequence of operations is kept, no information is lost.

# How ANDIE Was Tested
ANDIE was tested thoroughly by running the program through different devices and operating systems to ensure optimal performance and compatibility. Throughout the development process, each ANDIE feature was tested to identify and address any potential issues. It was also run to test usability to gather feedback and address user concerns that may be face as a result of using the program.

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

## ANDIE Build Instructions
Run ANDIE through Gradle.

## ANDIE User Guide 
**Shortcut Keys** - To use the shortcuts, you must click on the section of the menu where the action falls under before clicking the shortcut key. e.g. Save (S), Click File, then shortcut S.

**Image Save As, Image Export** - To use Save As and Export, you must put the image type after writing the new filename, e.g. a png type image with the file name imageFile must be named imageFile.png in order to save as or export the image.

**Mean Filter, Gaussian Blur Filter, Median Filter** - The number inputted for the radius of the image must be between 1 and 10.

**Resize** - The number inputted for the resize of the current image must be between 25 (a quarter of the current image size) and 300 (3 times the current image size).

**Colour Channel Cycling** - Cycles through different possibilities of the re-ordering of RGB e.g. RGB, GRB, BRG

**Choose Language** - In the help menu, click the Change Language button. A pop-up with a drop-down menu of the different possible languages is shown. Choose the language you want the ANDIE program to be and restart ANDIE.

**All Other Features** - Click the corresponding button for the feature or use the shortcut provided.

## Issues and Bugs
**Median Filter** - The Median Filter operation, while effective, may experience a delay in processing and application, this is particularly noticeable on larger images. This delay can extend up to approximately 10 seconds, depending on the size and complexity of the image. 
