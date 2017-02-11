import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

public class showserv extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
public showserv() 
{
super();
// TODO Auto-generated constructor stub
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}
protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException 
{
// TODO Auto-generated method stub 
PrintWriter out = res.getWriter();
res.setContentType("text/html");
out.println("<html><body>"); 
try 
{
Class.forName("com.mysql.jdbc.Driver");
String URL = "jdbc:mysql://localhost:3306/inventory";
Connection con = DriverManager.getConnection(URL, "root", "root"); // Here dsnname- mydsn,user id- system(for oracle 10g) 
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from item"); 
out.println("<head></head><body background='1111.PNG'></body>"); 
out.println("<center>");
out.println("<table border=1 width=50% height=50%>"); 
out.println("<tr><th>ITEMNAME</th><th>PRICE</th><th>QUANTITY</th><th>EXPIRY</th><th>CATEGORY</th><tr>");
while (rs.next()) 
{
String n = rs.getString("iname"); 
int p = rs.getInt("price");
int q = rs.getInt("quantity"); 
String e = rs.getString("expiry"); 
String c= rs.getString("category");
out.println("<tr><td>" + n + "</td><td>" + p + "</td><td>" + q + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
}
out.println("</table>");
out.println("</center>");
out.println("</html></body>");
con.close();
}
catch (Exception e) 
{
out.println("error");	
}
}   
}