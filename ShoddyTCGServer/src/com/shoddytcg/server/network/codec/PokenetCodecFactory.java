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

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Provides a custom implementation of a codec factory allowing optimal networking
 * @author shadowkanji
 *
 */
public class PokenetCodecFactory implements ProtocolCodecFactory {
	private final PokenetEncoder m_encoder;
	private final PokenetDecoder m_decoder;
	
	/**
	 * Default constructor.
	 */
	public PokenetCodecFactory() {
		m_encoder = new PokenetEncoder();
		m_decoder = new PokenetDecoder();
	}
	
	/**
	 * Returns the decoder
	 */
	public ProtocolDecoder getDecoder() throws Exception {
		return m_decoder;
	}

	/**
	 * Returns the encoder
	 */
	public ProtocolEncoder getEncoder() throws Exception {
		return m_encoder;
	}

	/**
	 * Return the default decoder
	 */
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return m_decoder;
	}

	/**
	 * Returns the default encoder
	 */
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return m_encoder;
	}

}
