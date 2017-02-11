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

public class insertitem extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
public insertitem() 
{
super();
// TODO Auto-generated constructor stub
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}
Connection conn = null; 
PreparedStatement ps = null;
ResultSet rs = null;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
// TODO Auto-generated method stub 
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
try
{
Class.forName("com.mysql.jdbc.Driver");
String URL = "jdbc:mysql://localhost:3306/inventory";
conn =(Connection) DriverManager.getConnection(URL, "root","root"); 
String name= request.getParameter("ittname");
String query ="select * from item where iname= ?"; 
ps = conn.prepareStatement(query); 
ps.setString(1,name);
rs=ps.executeQuery();
if(rs.next())
{ 
String s=rs.getString("iname"); 
if(name.equals(s))
{
out.print("<script> alert('item already present , may consider using update function'); </script>"); 
RequestDispatcher rd1=request.getRequestDispatcher("./modify.html");
rd1.include(request,response);
}
}
else
{
int p=Integer.parseInt(request.getParameter("iprice"));
int qt=Integer.parseInt(request.getParameter("iqty")); 
String e=request.getParameter("iexp");
String c=request.getParameter("cat");
int i=ps.executeUpdate(" insert into item values('"+name+"', '"+p+"','"+qt+"' ,'"+e+"','"+c+"')"); 
out.print("<script>alert('item inserted successfully');</script>");
RequestDispatcher rd1=request.getRequestDispatcher("./additem.html");
rd1.include(request,response);
}
conn.close();
ps.close();
rs.close();
}
catch(Exception e)
{
out.println(e);
}
}
}