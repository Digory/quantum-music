# quantum-music
Creates music based on the results of IBM's quantum computer. Really cool stuff!

----------------------------------------------------------------------------------------------------------------------
DISCLAIMER 
----------------------------------------------------------------------------------------------------------------------

I understand VERY little about quantum computing. I just wanted to make something that utilises the concept as I find the whole thing very exciting. Let me know if any of the explanations are wrong. 

---------------------------------------------------------------------------------------------------------------------
BACKGROUND
----------------------------------------------------------------------------------------------------------------------

IBM provides public access to a real quantum computer with something called the Quantum Experience (see here for more details: https://www.research.ibm.com/ibm-q/ ).  A quantum computer uses quantum bits (qubits) instead of classical bits. A qubit can take values of 0, 1, or, more interestingly, both at the same time, using superposition. IBM lets you access one of their 5-qubit machines through the above website; and anyone is able to. Pretty incredible! 

Qubits are controlled with logical gates just like classical bits are. However, the gates are a lot weirder and do strange things to create superpositions and phase shifts.

Once measured, just like a classical bit, a qubit has a definite value; but, when a qubit has previously been placed in a state of superposition, the value when measured may be different each time. Thus, calculations are repeated many times and, that way, you can distinguish what the most likely result of the calculation should be. The result you get most often has the highest probability of being right. When you use IBM's computer the experiment is repeated 1024 times.

---------------------------------------------------------------------------------------------------------------------
WHAT THE PROGRAM DOES:
---------------------------------------------------------------------------------------------------------------------

Handily, you can download the results of your experiment in a CSV file. This program evaluates the results of any 5-qubit experiment in that form. A 5-qubit system, once measured, will give a result of one out of thirty-two combinations, just like 5 normal bits. The program assigns each combination a MIDI code value in the range 60-91. It ranks each combination in order of likelihood based upon the results of the quantum calculation. Then, it creates beautiful (or not-so-beautiful) music by playing each note in decreasing order of probability.
 
The attached CSV file was an experiment ran on the IBM Q5 Tenerife (ibmqx4). The QASM file describes the experiment in the QASM language (what the Quantum Experience uses). It starts with all qubits having a value of 0. Then, they are each placed in a superposition state with use of a Hadamard gate ( https://en.wikipedia.org/wiki/Quantum_logic_gate#Hadamard_(H)_gate ) and measured.
