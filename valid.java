import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

public class valid extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
Connection conn = null;
PreparedStatement ps = null; 
ResultSet rs = null;
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
{
// TODO Auto-generated method stub 
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
try
{
Class.forName("com.mysql.jdbc.Driver");
String URL = "jdbc:mysql://localhost:3306/inventory"; 
String uname= request.getParameter("uname");
String pwd= request.getParameter("pwd"); 
if(uname.equals("root") && pwd.equals("root"))
{
RequestDispatcher rd1=request.getRequestDispatcher("./admin.html"); 
rd1.include(request,response);
}
else if(uname.equals("staff") && pwd.equals("staff"))
{
RequestDispatcher rd1=request.getRequestDispatcher("./bill.html"); 
rd1.include(request,response);
}
else
{
RequestDispatcher rd2=request.getRequestDispatcher("./login.html");
rd2.include(request,response);
out.print(" <script> alert('Invalid UserName or Password'); </script>");
}
rs.close();
ps.close();
conn.close();
}
catch(IOException e)
{
out.println(e);
}
}
}