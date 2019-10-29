<nav class="navbar navbar-expand-md bg-dark navbar-dark" >
  <!-- Brand -->
  <a class="navbar-brand" href="/">E-RAIL</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav ml-auto">
        <%
            //if the user is a donor then don't display all the options to the user.
            if(session.getAttribute("role")!=null){
                String role=session.getAttribute("role").toString();
                if(role.equals("1")){
        %>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Admin Panel
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="/dashboard">Dashboard</a>
                <a class="dropdown-item" href="/adminTrainMgmt">Train Management</a>
                <a class="dropdown-item" href="/adminStationMgmt">Station Management</a>
                <a class="dropdown-item" href="/adminUserMgmt">User Management</a>
                <a class="dropdown-item" href="/trainStationMapping">Assign Station</a>
                <a class="dropdown-item" href="/classMgmt">Class Management</a>
                <a class="dropdown-item" href="/report">Report</a>
            </div>
        </li>
        <%}
        }
        %>
     <li class="nav-item">
        <a class="nav-link" href="/">Home</a>
      </li>
        <li class="nav-item">
            <a class="nav-link" id="booking" href="/booking">Booking History</a>
        </li>
      <li class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
      </li>
    </ul>
  </div> 
</nav>