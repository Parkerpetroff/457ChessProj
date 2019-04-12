#Python NAP Server
import socket               # Import socket module
import _thread				# Import thread module

#Saves user key as Username, IP, port
users = {}

s = socket.socket()         # Create a socket object
s.bind(('localhost', 9000))        # Bind to the port
s.listen(4)

def commandParser(conn, addr,s):
	
	while True:
		command = conn.recv(1024).decode()
		split = command.split()
		print(split)

		if split[0] == "CONNECT":

			# Username, IP, Port
			users[split[3]] = [split[3], str(addr[0]), str(addr[1])]
			print("User Connected: " + users[addr[1]])

			# https://stackoverflow.com/questions/48266026/socket-java-client-python-server
			message = "connected".encode("UTF-8")
			conn.send(len(message).to_bytes(2, byteorder='big'))
			conn.send(message)
			
		elif split[0] == "SEARCH":
			print("Search")
			if split[1] in users:
				message = ' '.join(users[split[1]])
				conn.send(message.encode())
			else:
				message = "No Matching Records"
				conn.send(message.encode())

		elif split[0] == "TABLES":
			
			# Send the users table
			message = "Users\n"
			for k in users:
				message = message + ' '.join(users[k]) + "\n"
			conn.send(message.encode())
		elif split[0] == "LOGOUT": #QUIT

			message = "\nUser disconnected"
			conn.send(message.encode())
			#delete info from tables
			del users[addr[1]]
			conn.close()
			break

		elif split[0] == "STOP":
			message = "Server Shutting Down"
			conn.send(message.encode())
			exit()
			#conn.close() or quit()

		#invalid command
		else:
			# Send to client the number of data rows
			message = "Bad Command"
			conn.send(message.encode())
			conn.close()
			break
	print("Quit connection with:", addr)

while True:
	print("Waiting for connection...")
	conn, addr = s.accept()
	print("Got connection from:", addr)	
	message = "connected".encode("UTF-8")
	conn.send(len(message).to_bytes(2, byteorder='big'))
	conn.send(message)
	_thread.start_new_thread(commandParser, (conn, addr,s))
s.close()
