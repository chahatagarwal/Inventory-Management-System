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

public class addserv extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
public addserv() 
{
super();
// TODO Auto-generated constructor stub
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}
Connection conn=null;
PreparedStatement ps=null; 
ResultSet rs=null;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
// TODO Auto-generated method stub 
response.setContentType("text/html"); 
PrintWriter out=response.getWriter();
try
{
Class.forName("com.mysql.jdbc.Driver");
String URL="jdbc:mysql://localhost:3306/inventory"; 
conn=DriverManager.getConnection(URL,"root","root");
ps= conn.prepareStatement("delete from item where iname = ?"); 
String iname = request.getParameter("iname");
ps.setString(1, iname); 
int i=ps.executeUpdate(); 
if(i!=0)
{
out.print("<script>alert('item deleted');</script>"); 
RequestDispatcher rd1=request.getRequestDispatcher("./deleteitem.html");
rd1.include(request,response);
}
else
{
out.println("<script>alert('the item is either not present or deleted');</script>");
RequestDispatcher rd1=request.getRequestDispatcher("./deleteitem.html");
rd1.include(request,response); 
} 
rs.close();
ps.close();
conn.close();
}
}