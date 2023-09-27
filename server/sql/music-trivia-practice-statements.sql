use music_trivia;

select * 
from player p
join high_scores hs on p.player_id = hs.player_id;
