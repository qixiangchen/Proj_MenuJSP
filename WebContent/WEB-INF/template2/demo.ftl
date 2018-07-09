<html>  
<head>  
<title>Welcome FreeMarker!</title>  
</head>  
<body>  
    <h1>Welcome ${(name)}</h1>  
  
    <h1>Welcome ${user}<#if user == "Big Joe">, our beloved  
        leader</#if></h1>  
  
    <p>We have these animals:  
    <table border=1>  
        <#list students as animal>  
        <tr>  
            <td>${animal.name}  
            <td>${animal.age} Euros   
              
        </#list>  
    </table>  
      
<#list students>  
  <p>Fruits:  
  <ul>  
    <#items as fruit>  
      <li>${fruit.name}<#sep> and</#sep>  
    </#items>  
  </ul>  
<#else>  
  <p>We have no fruits.  
</#list>  
  
</body>  
</html> 