CrossServer
===========

Cross server communication framework for bukkit and sponge

#What is this?
This framework is maded to solve the synchronisation problems bungeecoord powered gaming networks have if it coms to sharing chat or remote commands.

#How does it work?
The CrossServer plugin needs to be connect to a CrossServer-server. This server will route all the communications cross servers. 
For example a player joins on Server1. Players on server2 want to /msg to the player. First off all the automated tab needs to be updated. 
This can be done by the CrossPlayerJoinEvent. Like the name already says, this event will be called if a players joins on another server.

#Do other plugins need modifactions?
Yes, installing this plugin will not allow other non-modificated plugins to manipulate other server

#I am a server owner, is this plugin usefully for me?
If another plugin depends this one, yes. Otherwise no.

#Will this support sponge?
Yes it will support sponge, but at this point it is more important to get it to work on 1.4.7 bukkit. 
