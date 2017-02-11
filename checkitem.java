import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

public class checkitem extends HttpServlet 
{ 
private static final long serialVersionUID = 1L;
public checkitem() 
{ 
super();
// TODO Auto-generated constructor stub
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
int i=0;
String query ="select * from item where iname= ?"; 
ps = conn.prepareStatement(query); 
ps.setString(1,name);
rs=ps.executeQuery();
if(rs.next())
{
String s=rs.getString("iname");
out.print(name);
if(name.equals(s))
{
out.print("<script>alert('item already present , may consider using update function');</script>"); 
i=1;
}
}
else
{
out.println("<script>alert(' use insert function');</script>");	
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