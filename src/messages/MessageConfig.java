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

/**
 *
 * @author Vova
 */
public class MessageConfig extends Message{
    private int numberOfInputNeuron, numberOfHiddenNeuron, rangeOfWeight;
    public MessageConfig(int numberOfInputNeuron,int numberOfHiddenNeuron,int rangeOfWeight){
                this.numberOfInputNeuron=numberOfInputNeuron;
                this.numberOfHiddenNeuron=numberOfHiddenNeuron;
                this.rangeOfWeight=rangeOfWeight;
    }

    /**
     * @return the numberOfInputNeuron
     */
    public int getNumberOfInputNeuron() {
        return numberOfInputNeuron;
    }

    /**
     * @return the numberOfHiddenNeuron
     */
    public int getNumberOfHiddenNeuron() {
        return numberOfHiddenNeuron;
    }

    /**
     * @return the rangeOfWeight
     */
    public int getRangeOfWeight() {
        return rangeOfWeight;
    }
    
    
}
