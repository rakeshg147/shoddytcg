/**
 *  Copyright (C) 2009 ShoddyTCG Developer Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shoddytcg.server.network.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;

/**
 * Decodes messages received from a client
 * @author shadowkanji
 *
 */
public class PokenetDecoder extends TextLineDecoder {
	
	/**
	 * Default constructor
	 */
	public PokenetDecoder() {
		super(Charset.forName("US-ASCII"), LineDelimiter.AUTO);
	}
	
	/**
	 * Decodes a message
	 */
	public void decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		super.decode(session, in, out);
	}
}
