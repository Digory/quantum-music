// Name of Experiment: Experiment #20180615130900 v3

OPENQASM 2.0;
include "qelib1.inc";

// Defining 5 qubits and 5 normal bits.
qreg q[5];
creg c[5];

// Placing a Hadamard gate on the circuit of each qubit.
h q[0];
h q[1];
h q[2];
h q[3];
h q[4];

// Measuring the result and putting it into the normal bits.
measure q[0] -> c[0];
measure q[1] -> c[1];
measure q[2] -> c[2];
measure q[3] -> c[3];
measure q[4] -> c[4];
