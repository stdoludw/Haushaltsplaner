<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h1>File Upload Form</h1> 
    <fieldset>
        <legend>Upload File</legend>
        <form action="CONTROLLER_Import" method="post" enctype="multipart/form-data">
            <label for="fileName">Select File: </label>
            <input id="fileName" type="file" name="fileName" size="30"/><br/>            
            <input type="submit" value="Upload"/>
        </form>
    </fieldset>
</body>
</html>