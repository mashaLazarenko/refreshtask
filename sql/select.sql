select lpad(' ', 3*level)||Name as IList 
from Linux 
start with ParentId = 0 
connect by prior Id = ParentId;

