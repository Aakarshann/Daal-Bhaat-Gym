<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String contextPath = request.getContextPath();
    %>
 
    
<!DOCTYPE html>
<html>

<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>

    <!-- Head section with link to CSS file -->
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/home.css" /> 

</head>

<body>
<%
    String userType = (String) session.getAttribute("userType");
    if (userType != null && userType.equals("admin")) {
        // Include admin header
%><jsp:include page="/pages/adminheader.jsp" />
        
<%
    } else if (userType != null && userType.equals("user")) {
        // Include regular header
        // You can use JSP include directive here
%><jsp:include page="/pages/header.jsp" />
<%
    } else {
        // No session, handle accordingly
%>
        <!-- Handle accordingly -->
<%
    }
%>
    
    

    <!-- Intro of the home page with video background and overlay text -->
    <div class="video">
        <video autoplay loop muted>
            <source src="<%=contextPath%>/resources/images/others/background(1).mp4" type="video/webm">
        </video>
        <div class="overlay">
            <h3>Daal Bhaat Power 24 Hour</h3>
            <p>It's about drvie its about power, we stay hungry we devour. Put in the work put in the hours.</p>
        </div>
    </div>

    <br><br>

    <!-- Table with image slider and text beside it -->
    <table class="slider-table">
        <tr>
            <!-- Image slider -->
            <td class="slider-cell">
                <div class="slider">
                    <div id="img">
                        <img src="<%=contextPath%>/resources/images/others/gym.jpeg">
                    </div>
                </div>
            </td>
            <!-- Text for slider -->
            <td>
                <div class="slidertxt">
                    <h3>Get BIGGER</h3>
                    <p>Transform your fitness journey with our comprehensive gym website, offering expert guidance and personalized workout plans for every level. Join our online community today and unlock your full potential for a healthier, stronger you.</p>
                </div>
            </td>
        </tr>
    </table>

    <!-- Introduction to the blog section of the home page -->
  <div class="intro"> 
    <table>
        <tr>
            <td class="firstintro">
            <img src="<%=contextPath%>/resources/images/user/${loggedInUser.imageUrlFromPart}" class="card-img-top" 
            alt="" onerror="this.onerror=null; this.src='<%=contextPath%>/resources/images/others/userimg.jpeg';">
            
                
                <p class="intropara">Username: ${loggedInUser.username} </p> <!-- Use EL to fetch username -->
                <p class="intropara">Full Name: ${loggedInUser.firstName} ${loggedInUser.lastName}</p> <!-- Use EL to fetch full name -->
                <p class="intropara">Email: ${loggedInUser.email}</p> <!-- Use EL to fetch email -->
                <p class="intropara">Contact: ${loggedInUser.phoneNumber}</p> <!-- Use EL to fetch phone number -->
                <br>
                <a href="html/Blog.html" class="buttonlink"><button type="button" class="button1">Update</button></a>
            </td>
            <td>
                <img class="persons" src="<%=contextPath%>/resources/images/others/gym_person.jpg">
            </td>
        </tr>
    </table>
</div>
     
    <!-- Footer section with company links and information -->
    <footer>
        <div class="ending">
            <h5>Country/Region: Nepal <span>&copy; 2024 PRINTFINITY. All rights reserved.</span></h5>
            <hr>
            <table>
                <tr>
                    <td>
                        Our Address
                    </td>
                    <td>
                        Products
                    </td>
                    <td>
                        Company
                    </td>
                    <td>
                        Find us on
                    </td>
                </tr>
                <tr>
                    <td>
                        Budhanilkantha<br>
                        Smakhushi<br>
                        Patan
                    </td>
                    <td>
                        Ink Printers<br>
                        Laser Printers<br>
                        Scanners
                    </td>
                    <td>
                        <a href="html/Products.html">Products</a><br>
                        <a href="html/Research.html">Research</a><br>
                        <a href="html/Blog.html">Blog</a><br>
                        <a href="html/About.html">About Us</a>
                    </td>
                    <td class="footerlinks">
                        <a href="#">FaceBook</a><br>
                        <a href="#">Instagram</a><br>
                        <a href="#">LinkedIn</a><br>
                        <a href="#">Youtube</a><br>
                        <a href="#">X</a><br>
                        Printfinity@gmail.com
                    </td>
                </tr>
            </table>
        </div>
    </footer>

</body>

</html>
