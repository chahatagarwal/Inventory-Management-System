import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

public class expirycalc extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
public expirycalc() 
{
super();
// TODO Auto-generated constructor stub
}
protected void doPost(ServletRequest request, HttpServletResponse res) throws ServletException, IOException 
{ 
// TODO Auto-generated method stub
PrintWriter out = res.getWriter(); 
res.setContentType("text/html"); 
out.println("<html><body>");
try 
{ 
Class.forName("com.mysql.jdbc.Driver");
String URL = "jdbc:mysql://localhost:3306/inventory";
Connection con = DriverManager.getConnection(URL, "root", "root"); // Here dsnname- mydsn,user id- system(for oracle 10g), 
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("call expcalc");
while (rs.next()) 
{
String n = rs.getString("iname");
int q = rs.getInt("quantity"); 
String e = rs.getString("expiry"); 
String c= rs.getString("category");
String msg="name= "+n+" Category= "+c+" expiry= "+e;
String to="yourmailid@host.com";
String sub=" Products that are soon to be Expired";
out.println("<head></head><body background='1 - Copy.jpg'></body>");
out.println("<tr><td>" + n + "</td><td>" + q + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
Mailer.send(to,sub, msg);	
}
RequestDispatcher rd1=request.getRequestDispatcher("./admin.html");
rd1.include(request,res);
out.println("<head><script>alert('Check Complete')</script></head>");
con.close();
}
}