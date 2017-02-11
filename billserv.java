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

public class billserv extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
public billserv() 
{
super();
// TODO Auto-generated constructor stub
}
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
conn=(Connection)DriverManager.getConnection(URL, "root", "root"); 
String iname= request.getParameter("itemname");
int quan=Integer.parseInt(request.getParameter("quan"));
String query = "update item set quantity=quantity - ? where iname=?"; 
ps = conn.prepareStatement(query);	
ps.setString(1,quan);
ps.setString(2,iname);
int i=ps.executeUpdate();
if(i!=0)
{	
out.println("<script>alert('goods obtained');</script>");
RequestDispatcher rd1=request.getRequestDispatcher("./bill.html");
rd1.include(request,response);
}	
else
{
out.println("</br>item not present"); 
RequestDispatcher rd1=request.getRequestDispatcher("./bill.html");
rd1.include(request,response);
}
conn.close();
}
catch(Exception e)
{
out.println(e);
}
}
}