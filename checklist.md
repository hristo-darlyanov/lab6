
# ✅ Lab Requirements Checklist

## 🖥️ Server Requirements

### Functional Requirements
- ✅ Objects in the collection are sorted by default.
- ✅ Use **Datagrams (UDP)** for communication.
- ✅ Listen for client connections and requests.
- ✅ Process incoming commands from clients.
- ✅ Save the collection to a file on application shutdown.
- ✅ Save collection to file using a **server-only command**.
- ✅ Log server operations using **Logback**:
    - ✅ Server start
    - ✅ Connection received
    - ✅ Request received
    - ✅ Response sent
- ✅ Server operates in **single-threaded mode**.

### Modules to Add
- ✅ Connection Acceptance Module
- ✅ Request Reading Module
- ✅ Command Processing Module
- ✅ Response Sending Module
- ✅ Logging Module

### Derived/Mandatory
- ✅ Handle file I/O exceptions gracefully.
- ✅ Implement shutdown hook to trigger saving.

### Legacy Behaviors (Integrate into appropriate modules)
- ✅ Work with collection file.
- ✅ Manage collection lifecycle and operations.
- ✅ Assign auto-generated fields to new objects.

## 💻 Client Requirements

### Functional Requirements
- ✅ Handle temporary server unavailability.
- ✅ Use **non-blocking** UDP channels.
- ✅ Serialize commands and arguments before sending.
- ✅ Send serialized commands to the server.
- ✅ Process and display server response.
- ✅ `save` command must be removed.
- ✅ `exit` command exits the client application.

### Modules to Add
- ✅ Network Communication Module
- ✅ Response Processing Module

### Derived/Mandatory
- ✅ Use `DatagramChannel` with `Selector`.
- ✅ Implement retry mechanism with backoff for unavailable server.
- ✅ Proper console I/O for responses and errors.

### Legacy Behaviors (Integrate into appropriate modules)
- ✅ Read commands from the console.
- ✅ Validate command input locally.

## 🌐 Common Requirements

### Functional Requirements
- ✅ Use Stream API and lambda expressions for collection operations.
- ✅ Object exchange must use **Java serialization**.
- ✅ All communication uses **UDP protocol**.

### Modules to Add
- ✅ Command Classes
- ✅ Model Classes
- ✅ Command Interface

### Derived/Mandatory
- ✅ Ensure all model and command classes implement `Serializable`.

## 🧾 Commands List

### Server-Side (Responses, processing commands)
- ✅ `SaveCommand` — SERVER ONLY
- ✅ `AddCommand`
- ✅ `AddIfMinCommand`
- [ ] `UpdateCommand`
- ✅ `ClearCommand`
- ✅ `CountByGenreCommand`
- ✅ `CountLessThanOscarsCommand`
- ✅ `ExecuteScriptCommand`
- ✅ `FilterContainsNameCommand`
- ✅ `RemoveByIdCommand`
- ✅ `RemoveHeadCommand`
- ✅ `RemoveLowerCommand`
- ✅ `HelpCommand`
- ✅ `InfoCommand`
- ✅ `ShowCommand`

### Client-Side (Requests, creation of objects and sending them)
- ✅ `ExitCommand` (terminate client locally)
- ✅ `AddCommand`
- ✅ `AddIfMinCommand`
- [ ] `UpdateCommand`
- ✅ `ClearCommand`
- ✅ `CountByGenreCommand`
- ✅ `CountLessThanOscarsCommand`
- ✅ `ExecuteScriptCommand`
- ✅ `FilterContainsNameCommand`
- ✅ `RemoveByIdCommand`
- ✅ `RemoveHeadCommand`
- ✅ `RemoveLowerCommand`
- ✅ `HelpCommand`
- ✅ `InfoCommand`
- ✅ `ShowCommand`
