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

public class updateitem extends HttpServlet 
{
private static final long serialVersionUID = 1L; 
public updateitem() 
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
// TODO Auto-generated method stub response.setContentType("text/html"); PrintWriter out = response.getWriter(); try
{
Class.forName("com.mysql.jdbc.Driver");
String URL = "jdbc:mysql://localhost:3306/inventory";
conn =(Connection) DriverManager.getConnection(URL, "root", "root");
String query = " update item set price=? , quantity= quantity + ? where iname=?";
String iname= request.getParameter("uiname");
String iprice= request.getParameter("iprice");
String iqty= request.getParameter("qty");
ps = conn.prepareStatement(query); 
int a=Integer.parseInt(iprice);
int b=Integer.parseInt(iqty);
ps.setInt(1,a);
ps.setInt(2,b);
ps.setString(3,iname);
ps.execute();
if(true)
{
out.println("<script>alert('You are Successfully Updated');</script>");
RequestDispatcher rd1=request.getRequestDispatcher("./modify.html");
rd1.include(request,response);
}
conn.close();
}
catch(Exception e)
{
out.println(e);
}
}