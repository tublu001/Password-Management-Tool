<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1> Set your password preference </h1> </center>
    <form name="passwordPreference" action="passwordPreference" method="post">
        <div class="container">
        <b>
            <input type="checkbox" name="includeUpperLetters" value="yes"> includeUpperLetters<BR>
            <input type="checkbox" name="includeLowerLetters" value="yes"> includeLowerLetters<BR>
            <input type="checkbox" name="includeNumbers" value="yes"> includeNumbers<BR>
            <input type="checkbox" name="includeSymbols" value="yes"> includeSymbols<BR>

            <select name="preferredPasswordLength" id="preferredPasswordLength">
                <optgroup label="Weak">
                  <option value="5">5</option>
                  <option value="8">8</option>
                </optgroup>
                <optgroup label="Medium">
                  <option value="12">12</option>
                  <option value="15">15</option>
                </optgroup>
                <optgroup label="Strong">
                  <option value="20">20</option>
                  <option value="25">25</option>
                </optgroup>
            </select>
              <br><br>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Set Password Preference</b></button><br>
            <div style="width: 20%"/>
            <a href="crudMenu" >Click here </a> for Menu...
        </b>
        </div>
    </form>
<%@ include file="footer.jsp" %>
