<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>


<body>
    <form action="/controller" method="post" name="login">
      <input type="hidden" name="command" value="registration"/>
      Login: <br/>
      <input type="text" name="username" value=""/> <br/>
      Password: <br/>
      <input type="password" name="password" value=""> <br/>
      Confirm password: <br/>
      <input type="password" name="password2" value=""> <br/>
      Email: <br/>
      <input type="text" name="email" value="">
      <input type="submit" value="Sign up">
    </form>
</body>
</html>
