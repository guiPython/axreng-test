# Html Search Engine
In this technical challenge, the goal was to find words in HTML pages. The idea was that given an input link, I should search for the requested word. 
In addition, it was necessary to map internal links and perform searches on them as well. 

Example:

* url: test.com.br/init
* term: "test"

If the page contained the term "test" we would increase the result. 
If it had a reference to a link in the form "test.com.br/xxx/..." we would have to keep the search active and access the content of this new reference.

For the problem, I thought of a competing approach. I was not familiar with the Akka|Elixir actor model, but I used the knowledge I gained using Golang. 
The idea was to think of an event-based communication, the term search processes send messages related to the "term found" and "url found" events, 
the handlers of these events coordinate calls to the methods of the search entity that I had to implement as "synchronized", 
so I can use the main thread as an orchestrator and controller of the search, always checking for a state changed by threads that it launched. 


The relationship between this solution and Golang was to think of the event dispatcher mentioned as a channel, 
basically receiving signals from several goroutines that would be the famous Java threads.

