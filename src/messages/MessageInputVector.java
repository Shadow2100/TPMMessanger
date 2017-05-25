/*
 * Copyright (C) 2017 Vova
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package messages;

import java.io.Serializable;
import treeparitymachinesalgorithm.InputVector;

/**
 *
 * @author Vova
 */
public class MessageInputVector extends Message implements Serializable{
    private InputVector inputVector;
    private int count=0;
    
    private int[] data;

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public int[] getData() {
        return data;
    }

    public MessageInputVector(int[] data) {
        this.data = data;
    }


    public MessageInputVector(InputVector inputVector) {
        this.inputVector=inputVector;
    }

    public InputVector getInputVector() {
        return inputVector;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    
    
    
}
