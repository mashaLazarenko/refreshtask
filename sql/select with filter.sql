select lpad(' ',level*3)||Name as IList
from Linux
where Id in (
    select distinct Id
    from Linux
    start with Id in (
        select Id
        from Linux
        where connect_by_isleaf = 1
        and Name like '%a%'
        start with ParentId = 0
        connect by prior Id = ParentId
        )
    connect by prior ParentId = Id
    )
start with ParentId = 0
connect by prior Id = ParentId;