var UserId = (function() {
    var Id= "";
  
    var getId = function() {
      return Id;    
    };
  
    var setId= function(name) {
        Id = name;     
      
    };
  
    return {
      getId: getId,
      setId: setId
    }
  
  })();
  
  export default UserId;