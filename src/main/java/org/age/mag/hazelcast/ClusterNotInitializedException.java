package org.age.mag.hazelcast;

@SuppressWarnings("serial")
/**
 * Exception to signal that method was trying to be executed on not initialized cluster instance.
 *
 */
public class ClusterNotInitializedException extends Exception {
    @SuppressWarnings("unused")
    private String detailMessage = "Cluster not initialized.";
}
